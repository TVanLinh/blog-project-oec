package service;

import dao.ConfigurationDAO;
import entities.Configuration;
import forms.ConfigForm;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class ConfigurationService extends AbstractService<Configuration>{

    @Autowired
    ConfigurationDAO configurationDAO;
    public static final String TITLE = "title";
    public static final String DATE_FORMAT = "date_format";
    public static final String NUMBER_POST_VIEW = "number_post_view";

    public ConfigurationService() {
    }

    public void save(Configuration configuration)
    {
        this.configurationDAO.update(configuration);
    }
    public void delete(int idConf)
    {
        this.configurationDAO.delete(idConf);
    }

    public Class<Configuration> getClassTable() {
        return Configuration.class;
    }

    public Configuration find(int id)
    {
        return this.find(id);
    }

    public List<Configuration> findAll()
    {
        return this.findAll();
    }

    public boolean isHaveFormatTime(Configuration config, String str) {
        if (config == null) {
            return false;
        }
        if (config.getValue().equalsIgnoreCase(str.trim())) {
            return true;
        }
        return false;
    }

    public Configuration findByName(String str) {
        String query = "select * from configuration where name = :name";
        Query<Configuration> configurationQuery = this.sessionFactory.getCurrentSession().createNativeQuery(query, Configuration.class);
        configurationQuery.setParameter("name", str);
        return configurationQuery.getSingleResult();
    }

    public Map<String, Configuration> getConfig() {
        Map<String, Configuration> hashMap = new HashMap<String, Configuration>();
        hashMap.put(TITLE, this.getConfigTitle());
        hashMap.put(DATE_FORMAT, this.getConfigDateFormat());
        hashMap.put(NUMBER_POST_VIEW, this.getConfigNumberView());
        return hashMap;
    }

    public Configuration getConfigTitle() {
        Configuration configuration = this.findByName(TITLE);
        if (configuration == null) {
            configuration = new Configuration();
            configuration.setName(TITLE);
            configuration.setValue("BlogTitle");
        }
        return configuration;
    }

    public Configuration getConfigDateFormat() {
        Configuration configuration = this.findByName(DATE_FORMAT);
        if (configuration == null) {
            configuration = new Configuration();
            configuration.setName(DATE_FORMAT);
            configuration.setValue("hh:MM:ss dd/MM/yyyy");
        }
        return configuration;
    }

    public Configuration getConfigNumberView() {
        Configuration configuration = this.findByName(NUMBER_POST_VIEW);
        if (configuration == null) {
            configuration = new Configuration();
            configuration.setName(NUMBER_POST_VIEW);
            configuration.setValue("4");
        }
        return configuration;
    }


    public Map<String, Configuration> getConfig(Map<String, Configuration> hashMap, ConfigForm configForm) {
        Configuration cof1, conf2, conf3;
        cof1 = hashMap.get(TITLE);
        cof1.setValue(configForm.getTitle());
        hashMap.put(TITLE, cof1);

        conf2 = hashMap.get(NUMBER_POST_VIEW);
        conf2.setValue(configForm.getNumberView());
        hashMap.put(NUMBER_POST_VIEW, conf2);

        conf3 = hashMap.get(DATE_FORMAT);
        conf3.setValue(configForm.getDateFormat());
        hashMap.put(DATE_FORMAT, conf3);
        return hashMap;
    }

    public void save(Map<String, Configuration> hashMap) {
        this.save(hashMap.get(TITLE));
        this.save(hashMap.get(DATE_FORMAT));
        this.save(hashMap.get(NUMBER_POST_VIEW));
    }
}
