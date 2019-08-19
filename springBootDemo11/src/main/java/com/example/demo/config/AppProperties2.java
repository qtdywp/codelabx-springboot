package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codelab")
public class AppProperties2
{
    private String bj;
    private String sh;

    @NestedConfigurationProperty
    private Province province;

    public static class Province
    {
        private String hb;
        private String sd;

        public String getHb()
        {
            return hb;
        }

        public void setHb(String hb)
        {
            this.hb = hb;
        }

        public String getSd()
        {
            return sd;
        }

        public void setSd(String sd)
        {
            this.sd = sd;
        }
    }


    public String getBj()
    {
        return bj;
    }

    public void setBj(String bj)
    {
        this.bj = bj;
    }

    public String getSh()
    {
        return sh;
    }

    public void setSh(String sh)
    {
        this.sh = sh;
    }

    public Province getProvince()
    {
        return province;
    }

    public void setProvince(Province province)
    {
        this.province = province;
    }
}
