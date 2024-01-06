package ru.practicum.dinner;

import java.util.HashSet;

public class Combo {
    HashSet<String> set;

    public Combo(HashSet<String> combo) {
        this.set = combo;
    }

    public HashSet<String> getSet(){
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combo combo1 = (Combo) o;
        if(set.size()!=combo1.getSet().size()) return false;
        for(String s : set){
            if(!combo1.getSet().contains(s)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (String s : set) {
            hash = hash + s.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        for(String s: set){
            res.append(s+", ");
        }
        return res.substring(0,res.length()-2).toString();
    }
}
