package com.demo.repository;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /* --------------- UPDATE ----------------------*/
    /**
     * Creeaza statement-ul pentru editare
     *
     * @param field campul identificare pentru care s edoreste edidarea
     * @param value valoarea corespunzatoare campului field
     * @return Un string ce reprezinta statement-ul SQL pentru editare
     */
    protected String createUpdateStatement(String field, String value) {

        try {
            Vector<Object> atrib = Reflection.retriveProperties(type.newInstance(), true);
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ");
            sb.append(type.getSimpleName());
            sb.append(" SET ");

            sb.append(" " + atrib.get(0) + " = ?");
            for (int i = 1; i < atrib.size(); i++) {
                sb.append(", " + atrib.get(i) + " = ?");
            }
            sb.append(" WHERE " + field + " = " + value);
            return sb.toString();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }

    /* --------------- INSERT ----------------------*/
    /**
     * Creeaza statement-ul pentru inserare
     *
     * @return Un string ce reprezinta statement-ul SQL pentru inserare
     */
    protected String insertStatement(T t) {

            Vector<Object> values = Reflection.retriveProperties(t, false);
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
            sb.append(type.getSimpleName());
            sb.append(" VALUES ( ");
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) instanceof String == true){
                    sb.append("'" + values.get(i) + "' , ");
                } else {
                    sb.append( values.get(i) + " , ");
                }
            }
            sb.delete(sb.length()-2, sb.length());
            sb.append(")");
            return sb.toString();
    }

    /**
     *Insereaza in baza de date
     * @param t obiectul ce trebuie inserat
     * @return obiectul inserat
     */
    public T insert(T t) {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = insertStatement(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO: insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /* --------------- FIND ----------------------*/
    /**
     * Creeaza statement-ul pentru interogare
     *
     * @return Un string ce reprezinta interogarea SQL
     */
    protected String createFindAllQuery(String findText) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" " + findText + " ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Creeaza statement-ul pentru interogare
     *
     * @param field parametrul dupa care se cauta.
     * @return Un string ce reprezinta statement-ul SQL pentru interogare
     */
    private String createFindByIdQuery(String field, String value) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =" + value);
        return sb.toString();
    }

    /**
     * Returneaza o lista de obiecte ca raspuns al interogarii.
     *
     * @param find campul dupa care se cauta
     * @return lista de obiecte
     */
    public List<T> findAll(String find) {

        List<T> results = new ArrayList<T>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAllQuery(find);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            results = createObjects(resultSet);
            return results;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param id
     * @param value
     * @return
     */
    public List<T> findAllById(String id, String value){
        List<T> results = new ArrayList<T>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindByIdQuery(id, value);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            results = createObjects(resultSet);
            return results;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAllById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creeaza a lista o obiecte ca rezultat al interogarii.
     *
     * @param resultSet rezultatul interogarii
     * @return lista de obiecte
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();

                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName()); // numele atributului
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type); // metoda de
                    // set
                    // pentru un
                    // atribut
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value); // apel set
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* --------------- DELETE ----------------------*/
    /**
     * Creeaza statement-ul pentru stergere
     *
     * @param field campul dupa care se doreste stergerea
     * @param value vaoarea campului field
     * @return Un string ce reprezinta statement-ul SQL pentru stergere
     */
    private String createDeleteByIdStatement(String field, String value) {

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "= " + value);
        return sb.toString();
    }

    /**
     * Realizeaza stergerea dupa un id specificat
     *
     * @param field campul dupa care se realizeaza stergerea
     * @param value valoare campului field
     * @return -1 pentru esec si un numar diferit de -1 pentru succes
     */
    public int deleteByID(String field, String value) {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteByIdStatement(field, value);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            return statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:DeleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    /**
     * Realizeaza stergerea tuturor inregistrarilor din tabela
     * @return -1 pentru esec si un numar diferit de -1 pentru succes
     */
    public int deleteAll() {
        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        String query = sb.toString();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            return statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:DeleteAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }
}
