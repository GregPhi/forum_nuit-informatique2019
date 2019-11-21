package com.example.forumnuitdelinformatique.objet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "proposition_table",
        foreignKeys = @ForeignKey(entity = Company.class,
                parentColumns = "id",
                childColumns = "company_id"))
public class Proposition implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nom;
    private String form_search;
    private String description;

    @ColumnInfo(name = "company_id") public int companyId;

    public Proposition(){
        this.nom = "";
        this.form_search = "";
        this.description = "";
    }

    public Proposition(String n, String f, String d){
        this.nom = n;
        this.form_search = f;
        this.description = d;
    }

    public void setId(int i){ this.id = i;}
    public void setNom(String n){ this.nom = n;}
    public void setForm_search(String f){ this.form_search = f;}
    public void setDescription(String d){ this.description = d;}

    public int getId(){ return this.id;}
    public String getNom(){
        return this.nom;
    }
    public String getDescription(){
        return this.description;
    }
    public String getForm_search(){ return this.form_search; }

    public void setCompanyId(int i){ this.companyId = i;}
    public int getCompanyId(){ return this.companyId;}

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Proposition createFromParcel(Parcel in) {
            return new Proposition(in);
        }

        public Proposition[] newArray(int size) {
            return new Proposition[size];
        }
    };

    public Proposition(Parcel in){
        this.nom = in.readString();
        this.form_search = in.readString();
        this.description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nom);
        dest.writeString(this.form_search);
        dest.writeString(this.description);
    }
}
