package br.com.renanstefanidev.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    // static faz com que não precisamos instanciar a nossa classe
    public static void copyNonNullProperties(Object source, Object target) {

        // Copiando valores não nulos e especificando de onde veio e para onde vai (source e target)
        // Valores nulos já tratados pelo método getNullPropertyNames serão mesclados
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        
    }


    // Método responsável por updates parciais
    public static String[] getNullPropertyNames(Object source) {

        // BeanWrapper é uma inteface do java que fornece uma forma de acessarmos as propriedades de um objeto
        // BeanWrapperImpl é a implementação dessa interface
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Gerando um array com todas as propriedades resgatadas
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Preparando conjunto para receber propriedades com valores nulos
        Set<String> emptyNames = new HashSet<>();
        
        // Verificando se a propriedade é null
        for(PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            // Caso a propriedade seja null, inserimos em emptyNames
            if(srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        // Criamos um array definindo a length de acordo com o num de propriedades adicionadas a emptyNames
        String[] result = new String[emptyNames.size()];

        // Inserimos os dados de emptyNames no array criado e retornamos o mesmo
        return emptyNames.toArray(result);
    }
}
