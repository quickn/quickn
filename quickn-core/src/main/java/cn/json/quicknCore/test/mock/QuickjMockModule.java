package cn.json.quicknCore.test.mock;


import cn.json.quicknCore.annotation.Transaction;
import cn.json.quicknCore.common.Setting;
import cn.json.quicknCore.guice.RequestScoped;
import cn.json.quicknCore.guice.SessionScoped;
import cn.json.quicknCore.guice.TransactionInterceptor;
import cn.json.quicknCore.session.Session;
import cn.json.quicknCore.test.QuickjActionTestCase;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import org.hibernate.SessionFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

/**
 * guice module缺省注入Hibernate Session,HttpSession，Request,Response.
 * 
 * @author Administrator
 * 
 */
public class QuickjMockModule extends AbstractModule {
	private boolean transcatable;
	public QuickjMockModule() {
		transcatable = true;
	}

	public QuickjMockModule(boolean transcatable) {
		this.transcatable = transcatable;
	}

	@Override
	protected void configure() {
		// Scopes.
		bindScope(RequestScoped.class, REQUEST);
		bindScope(SessionScoped.class, SESSION);
		// Bind request.
		Provider<HttpServletRequest> requestProvider = new Provider<HttpServletRequest>() {
			public HttpServletRequest get() {
				return QuickjActionTestCase.getRequest();
			}

			public String toString() {
				return "RequestProvider";
			}
		};
		bind(HttpServletRequest.class).toProvider(requestProvider);
		bind(ServletRequest.class).toProvider(requestProvider);
		if (transcatable)
			bindInterceptor(any(), annotatedWith(Transaction.class),
					new TransactionInterceptor());
		// Bind response.
		Provider<HttpServletResponse> responseProvider = new Provider<HttpServletResponse>() {
			public HttpServletResponse get() {
				return QuickjActionTestCase.getResponse();
			}

			public String toString() {
				return "ResponseProvider";
			}
		};
		bind(HttpServletResponse.class).toProvider(responseProvider);
		bind(ServletResponse.class).toProvider(responseProvider);
		// Bind session.
		Provider<Session> sessionProvider = new Provider<Session>() {
			public Session get() {
				return QuickjActionTestCase.getSession();
			}

			public String toString() {
				return "SessionProvider";
			}
		};
		bind(Session.class).toProvider(sessionProvider);
		if (Setting.usedb) {
			bind(SessionFactory.class).toProvider(
					new Provider<SessionFactory>() {
						public SessionFactory get() {
							return Setting.sessionFactory;
						}

						public String toString() {
							return "SessionFactoryProver";
						}
					});
		}
	}
	  /**
	   * HTTP servlet request scope.
	   */
	  public static final Scope REQUEST = new Scope() {
	    public <T> Provider<T> scope(Key<T> key, final Provider<T> creator) {
	      final String name = key.toString();
	      return new Provider<T>() {
	        public T get() {
	          HttpServletRequest request = QuickjActionTestCase.getRequest();
	          synchronized (request) {
	            @SuppressWarnings("unchecked")
	            T t = (T) request.getAttribute(name);
	            if (t == null) {
	              t = creator.get();
	              request.setAttribute(name, t);
	            }
	            return t;
	          }
	        }
	      };
	    }

	    public String toString() {
	      return "ServletScopes.REQUEST";
	    }
	  };

	  /**
	   * HTTP session scope.
	   */
	  public static final Scope SESSION = new Scope() {
	    public <T> Provider<T> scope(Key<T> key, final Provider<T> creator) {
	      final String name = key.toString();
	      return new Provider<T>() {
	        public T get() {
	          Session session = QuickjActionTestCase.getSession();
	          synchronized (session) {
	            @SuppressWarnings("unchecked")
	            T t = (T) session.get(name);
	            if (t == null) {
	              t = creator.get();
	              session.set(name, t);
	            }
	            return t;
	          }
	        }
	      };
	    }

	    public String toString() {
	      return "ServletScopes.SESSION";
	    }
	  };	
}
