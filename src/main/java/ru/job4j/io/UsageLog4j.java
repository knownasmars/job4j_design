package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Mars Suleimanov";
        int age = 31;
        long moneyBalance = 999999999L;
        float weight = 83.5f;
        double height = 177.0;
        byte children = 0;
        short shoeSize = 42;
        char personalSign = 'S';
        boolean isDeveloper = true;

        LOG.debug("User info "
                        + "name : {}, age : {}, moneyBalance : {}, weight : {}, "
                        + "height : {}, children : {}, shoeSize : {}, personalSize : {}, isDeveloper : {}",
                name, age, moneyBalance, weight, height,
                children, shoeSize, personalSign, isDeveloper);
    }
}