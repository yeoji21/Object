package com.toby.spring;

public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public AccountDao accountDao() {
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao() {
        return new MessageDao(connectionMaker());
    }

    private DConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    private class AccountDao {
        public AccountDao(ConnectionMaker connectionMaker) {
        }
    }

    private class MessageDao {
        public MessageDao(ConnectionMaker connectionMaker) {
        }
    }
}
