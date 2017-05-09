package DAO;

import Entities.Configuration;

import java.util.List;

/**
 * Created by linhtran on 08/05/2017.
 */

public interface ConfigurationDAO {
    boolean insert(Configuration configuration);
    boolean update(Configuration configuration);
    boolean delete(int idConf);
}
