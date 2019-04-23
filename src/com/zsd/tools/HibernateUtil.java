package com.zsd.tools;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static final SessionFactory sessionFactory;

    static
	{
        try
		{
            //采用默认的hibernate.cfg.xml来启动一个Configuration的实�?
			Configuration configuration=new Configuration().configure();
			//由Configuration的实例来创建�?��SessionFactory实例
            sessionFactory = configuration.buildSessionFactory();
        }
		catch (Throwable ex)
		{
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

	@SuppressWarnings("rawtypes")
	public static final ThreadLocal session = new ThreadLocal();

    @SuppressWarnings("unchecked")
	public static Session currentSession() throws HibernateException
	{
        Session s = (Session) session.get();
        //如果该线程还没有Session,则创建一个新的Session
        if (s == null)
		{
            s = sessionFactory.openSession();
            //将获得的Session变量存储在ThreadLocal变量session�?
            session.set(s);
        }

        return s;
    }

    @SuppressWarnings("unchecked")
	public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        if (s != null)
            s.close();
        session.set(null);
    }
}
