package org.acme.my.ext;

public interface MyGenericInterface<Number_ extends Number,Text_> {
    Number_ extractNumber(Text_ text);
}
