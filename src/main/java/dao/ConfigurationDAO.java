package dao;

import entities.Configuration;

/**
 * Created by linhtran on 08/05/2017.
 */

public interface ConfigurationDAO {
    boolean insert(Configuration configuration);
    boolean update(Configuration configuration);
    boolean delete(int idConf);
}
