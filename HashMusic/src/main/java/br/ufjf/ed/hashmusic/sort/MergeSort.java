/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.ed.hashmusic.sort;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author -Igor
 */
public class MergeSort {
    
    public List<String> sort(List<String> strings){
        if(strings.size() <= 1)
            return strings;
        
        final int TAM = strings.size();
        final int HALF = TAM / 2;
        
        List<String> l1 = sort(strings.subList(0, HALF));
        List<String> l2 = sort(strings.subList(HALF, TAM));
        
        List<String> merge = merge(l1,l2);
        
        return merge;
    }
    
    private List<String> merge(List<String> vetor1, List<String> vetor2){
        if(vetor1 == null || vetor1.isEmpty())
            return vetor2;
        
        if(vetor2 == null || vetor2.isEmpty())
            return vetor1;
        
        //Iterador dos vetores
        int it1 = 0;
        int it2 = 0;
        //Tamanho do vetor
        final int TAM1 = vetor1.size();
        final int TAM2 = vetor2.size();
        //Lista que será a união dos dois vetores
        ArrayList<String> merged = new ArrayList<>();
        //String a ser inserida
        String st1;
        String st2;
        
        while(it1 < TAM1 && it2 < TAM2){
            st1 = vetor1.get(it1);
            st2 = vetor2.get(it2);
            int compare = st1.compareToIgnoreCase(st2);
            if(compare >= 0){
                merged.add(st2);
                it2++;
            } else {
                merged.add(st1);
                it1++;
            }
        }
        
        while(it1 < TAM1){
            st1 = vetor1.get(it1);
            merged.add(st1);
            it1++;
        }
        
        while(it2 < TAM2){
            st2 = vetor2.get(it2);
            merged.add(st2);
            it2++;
        }
        
        return merged;
    }
}
