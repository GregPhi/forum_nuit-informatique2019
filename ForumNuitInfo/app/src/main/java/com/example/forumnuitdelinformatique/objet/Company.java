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
    private String postes;
    private String logo;

    public Company(){
        this.nom = "";
        this.description = "";
        this.postes = "";
        this.logo = "";
    }

    public Company(String n, String d, String p, String l){
        this.nom = n;
        this.description = d;
        this.postes = p;
        this.logo = l;
    }

    public void setId(int i){ this.id = i;}
    public void setNom(String n){ this.nom = n;}
    public void setDescription(String d){ this.description = d;}
    public void setPostes(String p){ this.postes = p;}
    public void setLogo(String l){ this.logo = l; }

    public int getId(){ return this.id;}
    public String getNom(){
        return this.nom;
    }
    public String getPostes(){
        return this.postes;
    }
    public String getDescription(){ return this.description; }
    public String getLogo(){ return this.logo; }

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
        this.postes = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nom);
        dest.writeString(this.description);
        dest.writeString(this.postes);
    }
}
