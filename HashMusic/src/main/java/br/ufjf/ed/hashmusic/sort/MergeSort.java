/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ed.hashmusic.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author -Igor
 */
public class MergeSort 
{
    public <T> List<T> sort(List<T> listToSort, Comparator<T> comparator)
    {
        if(listToSort == null)
            return null;
        
        if(listToSort.size() <= 1)
            return listToSort;
        
        final int TAM = listToSort.size();
        final int HALF = TAM / 2;

        List<T> l1 = sort(listToSort.subList(0, HALF), comparator);
        List<T> l2 = sort(listToSort.subList(HALF, TAM), comparator);

        return merge(l1, l2, comparator);
    }
    
    private <T> List<T> merge(List<T> vetor1, List<T> vetor2, Comparator<T> comparator) 
    {
        if (vetor1 == null || vetor1.isEmpty()) 
        {
            return vetor2;
        }

        if (vetor2 == null || vetor2.isEmpty())
        {
            return vetor1;
        }

        //Iterador dos vetores
        int it1 = 0;
        int it2 = 0;
        
        //Tamanho do vetor
        final int TAM1 = vetor1.size();
        final int TAM2 = vetor2.size();
        
        //Lista que será a união dos dois vetores
        ArrayList<T> merged = new ArrayList<>();
        
        //String a ser inserida
        T st1;
        T st2;

        while (it1 < TAM1 && it2 < TAM2) 
        {
            st1 = vetor1.get(it1);
            st2 = vetor2.get(it2);
            int compare = comparator.compare(st1, st2);
            
            if (compare >= 0) 
            {
                merged.add(st2);
                it2++;
            } 
            else 
            {
                merged.add(st1);
                it1++;
            }
        }

        while (it1 < TAM1) 
        {
            st1 = vetor1.get(it1);
            merged.add(st1);
            it1++;
        }

        while (it2 < TAM2) 
        {
            st2 = vetor2.get(it2);
            merged.add(st2);
            it2++;
        }

        return merged;
    }
}