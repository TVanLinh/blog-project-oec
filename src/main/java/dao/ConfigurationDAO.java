package dao;

import entities.Configuration;

import java.util.List;

/**
 * Created by linhtran on 08/05/2017.
 */

public interface ConfigurationDAO {

    void update(Configuration configuration);
    void delete(int idConf);
     Configuration find(int id);
     List<Configuration> getAllConfiguration() ;
}
