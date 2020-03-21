package com.demo.repository;

import java.lang.reflect.Field;
import java.util.Vector;

public class Reflection {

    /**
     *
     * @param object obiectul din care se doreste extragerea valorii/ atributelor
     * @param head   determina extragerea numelor atributelor (head = true) sau a
     *               valorilor atributelor (head = false)
     * @return un vector de obiecte ce reprezinta rezultatul
     */
    public static Vector<Object> retriveProperties(Object object, boolean head) {

        Vector<Object> line = new Vector<Object>();
        for (Field field : object.getClass().getDeclaredFields()) {

            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                if (head == true) {
                    line.addElement(field.getName());
                } else {
                    line.addElement(value);
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return line;
    }
}
