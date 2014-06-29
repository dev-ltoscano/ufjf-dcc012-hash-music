/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ed.hashmusic.sort;

import br.ufjf.ed.hashmusic.viewmodel.component.comparator.IComparatorCompose;
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

        List<T> mergeList;
        
        if(!(comparator instanceof IComparatorCompose))
            mergeList = merge(l1, l2, comparator);
        else
        {
            mergeList = mergeCompose(l1, l2, (IComparatorCompose)comparator);
        }

        return mergeList;
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
    
    private <T> List<T> mergeCompose(List<T> vetor1, List<T> vetor2, IComparatorCompose<T> comparable)
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
        
        //MusicInfo a ser inserida
        T msc1;
        T msc2;

        while (it1 < TAM1 && it2 < TAM2) 
        {
            msc1 = vetor1.get(it1);
            msc2 = vetor2.get(it2);
            
            int[] compared = comparable.compareCompose(msc1, msc2);
            
            if (compared[0] == 0) 
            {
                int compare2 = compared[1];
                
                if (compare2 >= 0) 
                {
                    merged.add(msc2);
                    it2++;
                } 
                else 
                {
                    merged.add(msc1);
                    it1++;
                }
            } 
            else if (compared[0] > 0) 
            {
                merged.add(msc2);
                it2++;
            } 
            else 
            {
                merged.add(msc1);
                it1++;
            }
        }

        while (it1 < TAM1) 
        {
            msc1 = vetor1.get(it1);
            merged.add(msc1);
            it1++;
        }

        while (it2 < TAM2) 
        {
            msc2 = vetor2.get(it2);
            merged.add(msc2);
            it2++;
        }

        return merged;
    }
}