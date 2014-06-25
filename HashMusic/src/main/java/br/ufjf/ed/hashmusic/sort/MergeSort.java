/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ed.hashmusic.sort;

import br.ufjf.ed.hashmusic.model.MusicInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author -Igor
 */
public class MergeSort {

    public List<String> sort(List<String> strings) {
        if (strings == null) {
            return null;
        }
        if (strings.size() <= 1) {
            return strings;
        }

        final int TAM = strings.size();
        final int HALF = TAM / 2;

        List<String> l1 = sort(strings.subList(0, HALF));
        List<String> l2 = sort(strings.subList(HALF, TAM));

        List<String> merge = merge(l1, l2);

        return merge;
    }

    private List<String> merge(List<String> vetor1, List<String> vetor2) {
        if (vetor1 == null || vetor1.isEmpty()) {
            return vetor2;
        }

        if (vetor2 == null || vetor2.isEmpty()) {
            return vetor1;
        }

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

        while (it1 < TAM1 && it2 < TAM2) {
            st1 = vetor1.get(it1);
            st2 = vetor2.get(it2);
            int compare = st1.compareToIgnoreCase(st2);
            if (compare >= 0) {
                merged.add(st2);
                it2++;
            } else {
                merged.add(st1);
                it1++;
            }
        }

        while (it1 < TAM1) {
            st1 = vetor1.get(it1);
            merged.add(st1);
            it1++;
        }

        while (it2 < TAM2) {
            st2 = vetor2.get(it2);
            merged.add(st2);
            it2++;
        }

        return merged;
    }

    public List<MusicInfo> sortByArtist(List<MusicInfo> musics) {
        if (musics == null) {
            return null;
        }
        if (musics.size() <= 1) {
            return musics;
        }

        final int TAM = musics.size();
        final int HALF = TAM / 2;

        List<MusicInfo> l1 = sortByArtist(musics.subList(0, HALF));
        List<MusicInfo> l2 = sortByArtist(musics.subList(HALF, TAM));

        List<MusicInfo> merge = mergeByArtist(l1, l2);

        return merge;
    }

    public List<MusicInfo> mergeByArtist(List<MusicInfo> vetor1, List<MusicInfo> vetor2) {
        if (vetor1 == null || vetor1.isEmpty()) {
            return vetor2;
        }

        if (vetor2 == null || vetor2.isEmpty()) {
            return vetor1;
        }

        //Iterador dos vetores
        int it1 = 0;
        int it2 = 0;
        //Tamanho do vetor
        final int TAM1 = vetor1.size();
        final int TAM2 = vetor2.size();
        //Lista que será a união dos dois vetores
        ArrayList<MusicInfo> merged = new ArrayList<>();
        //MusicInfo a ser inserida
        MusicInfo msc1;
        MusicInfo msc2;

        while (it1 < TAM1 && it2 < TAM2) {
            msc1 = vetor1.get(it1);
            msc2 = vetor2.get(it2);
            int compare = msc1.getArtist().compareToIgnoreCase(msc2.getArtist());
            if (compare >= 0) {
                merged.add(msc2);
                it2++;
            } else {
                merged.add(msc1);
                it1++;
            }
        }

        while (it1 < TAM1) {
            msc1 = vetor1.get(it1);
            merged.add(msc1);
            it1++;
        }

        while (it2 < TAM2) {
            msc2 = vetor2.get(it2);
            merged.add(msc2);
            it2++;
        }

        return merged;
    }

    public List<MusicInfo> sortByAlbum(List<MusicInfo> musics) {
        if (musics == null) {
            return null;
        }
        if (musics.size() <= 1) {
            return musics;
        }

        final int TAM = musics.size();
        final int HALF = TAM / 2;

        List<MusicInfo> l1 = sortByAlbum(musics.subList(0, HALF));
        List<MusicInfo> l2 = sortByAlbum(musics.subList(HALF, TAM));

        List<MusicInfo> merge = mergeByAlbum(l1, l2);

        return merge;
    }

    public List<MusicInfo> mergeByAlbum(List<MusicInfo> vetor1, List<MusicInfo> vetor2) {
        if (vetor1 == null || vetor1.isEmpty()) {
            return vetor2;
        }

        if (vetor2 == null || vetor2.isEmpty()) {
            return vetor1;
        }

        //Iterador dos vetores
        int it1 = 0;
        int it2 = 0;
        //Tamanho do vetor
        final int TAM1 = vetor1.size();
        final int TAM2 = vetor2.size();
        //Lista que será a união dos dois vetores
        ArrayList<MusicInfo> merged = new ArrayList<>();
        //MusicInfo a ser inserida
        MusicInfo msc1;
        MusicInfo msc2;

        while (it1 < TAM1 && it2 < TAM2) {
            msc1 = vetor1.get(it1);
            msc2 = vetor2.get(it2);
            int compare = msc1.getAlbum().compareToIgnoreCase(msc2.getAlbum());
            if (compare >= 0) {
                merged.add(msc2);
                it2++;
            } else {
                merged.add(msc1);
                it1++;
            }
        }

        while (it1 < TAM1) {
            msc1 = vetor1.get(it1);
            merged.add(msc1);
            it1++;
        }

        while (it2 < TAM2) {
            msc2 = vetor2.get(it2);
            merged.add(msc2);
            it2++;
        }

        return merged;
    }

    public List<MusicInfo> sortByArtistAlbum(List<MusicInfo> musics) {
        if (musics == null) {
            return null;
        }
        if (musics.size() <= 1) {
            return musics;
        }

        final int TAM = musics.size();
        final int HALF = TAM / 2;

        List<MusicInfo> l1 = sortByArtistAlbum(musics.subList(0, HALF));
        List<MusicInfo> l2 = sortByArtistAlbum(musics.subList(HALF, TAM));

        List<MusicInfo> merge = mergeByArtistAlbum(l1, l2);

        return merge;
    }

    public List<MusicInfo> mergeByArtistAlbum(List<MusicInfo> vetor1, List<MusicInfo> vetor2) {
        if (vetor1 == null || vetor1.isEmpty()) {
            return vetor2;
        }

        if (vetor2 == null || vetor2.isEmpty()) {
            return vetor1;
        }

        //Iterador dos vetores
        int it1 = 0;
        int it2 = 0;
        //Tamanho do vetor
        final int TAM1 = vetor1.size();
        final int TAM2 = vetor2.size();
        //Lista que será a união dos dois vetores
        ArrayList<MusicInfo> merged = new ArrayList<>();
        //MusicInfo a ser inserida
        MusicInfo msc1;
        MusicInfo msc2;

        while (it1 < TAM1 && it2 < TAM2) {
            msc1 = vetor1.get(it1);
            msc2 = vetor2.get(it2);
            int compare = msc1.getArtist().compareToIgnoreCase(msc2.getArtist());
            if (compare == 0) {
                int compare2 = msc1.getAlbum().compareToIgnoreCase(msc2.getAlbum());
                if (compare2 >= 0) {
                    merged.add(msc2);
                    it2++;
                } else {
                    merged.add(msc1);
                    it1++;
                }
            } else if (compare > 0) {
                merged.add(msc2);
                it2++;
            } else {
                merged.add(msc1);
                it1++;
            }
        }

        while (it1 < TAM1) {
            msc1 = vetor1.get(it1);
            merged.add(msc1);
            it1++;
        }

        while (it2 < TAM2) {
            msc2 = vetor2.get(it2);
            merged.add(msc2);
            it2++;
        }

        return merged;
    }
}
