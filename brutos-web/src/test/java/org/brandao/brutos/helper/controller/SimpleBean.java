/*
 * Brutos Web MVC http://brutos.sourceforge.net/
 * Copyright (C) 2009 Afonso Brandao. (afonso.rbn@gmail.com)
 *
 * This library is free software. You can redistribute it
 * and/or modify it under the terms of the GNU General Public
 * License (GPL) version 3.0 or (at your option) any later
 * version.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Distributed WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 */

package org.brandao.brutos.helper.controller;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Brandao
 */
public class SimpleBean {

    private String arg;
    private int arg2;
    private SimpleBean bean;
    private EnumTest enumTest;
    private Calendar calendar;
    private Date date;

    public SimpleBean(){
    }

    public SimpleBean(String arg){
        this.arg = arg;
    }

    public SimpleBean(Calendar arg){
        this.calendar = arg;
    }

    public SimpleBean(Date arg){
        this.date = arg;
    }

    public SimpleBean(EnumTest enumTest){
        this.enumTest = enumTest;
    }

    public SimpleBean(int arg2){
        this.arg2 = arg2;
    }

    public SimpleBean(SimpleBean arg3){
        this.bean = arg3;
    }

    public SimpleBean(String arg,int arg2,SimpleBean bean){
        this.arg = arg;
        this.arg2 = arg2;
        this.bean = bean;
    }

    public SimpleBean(String arg,int arg2){
        this(arg,arg2,null);
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public SimpleBean getBean() {
        return bean;
    }

    public void setBean(SimpleBean bean) {
        this.bean = bean;
    }

    public EnumTest getEnumTest() {
        return enumTest;
    }

    public void setEnumTest(EnumTest enumTest) {
        this.enumTest = enumTest;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
