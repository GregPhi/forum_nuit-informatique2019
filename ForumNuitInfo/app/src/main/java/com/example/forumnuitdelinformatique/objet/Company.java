package com.example.forumnuitdelinformatique.objet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "company_table")
public class Company implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nom;
    private String description;
    private int logo;
    private boolean interessed = false;

    public Company(){
        this.nom = "";
        this.description = "";
        this.logo = -666;
    }

    public Company(String n, String d, int l){
        this.nom = n;
        this.description = d;
        this.logo = l;
    }

    public void setId(int i){ this.id = i;}
    public void setNom(String n){ this.nom = n;}
    public void setDescription(String d){ this.description = d;}
    public void setLogo(int l){ this.logo = l; }
    public void setInteressed(boolean i){ this.interessed = i;}

    public int getId(){ return this.id;}
    public String getNom(){
        return this.nom;
    }
    public String getDescription(){ return this.description; }
    public int getLogo(){ return this.logo; }
    public boolean getInteressed(){ return this.interessed;}

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public Company(Parcel in){
        this.nom = in.readString();
        this.description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nom);
        dest.writeString(this.description);
    }
}
