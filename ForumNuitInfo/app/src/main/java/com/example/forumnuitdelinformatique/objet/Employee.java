package com.example.forumnuitdelinformatique.objet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee_table",
        foreignKeys = @ForeignKey(entity = Company.class,
                                  parentColumns = "id",
                                  childColumns = "company_id")
)
public class Employee implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nom;
    private String prenom;
    private String mail;
    private String poste;

    @ColumnInfo(name = "company_id") public int companyId;

    public Employee(){
        this.nom = "";
        this.prenom = "";
        this.mail = "";
        this.poste = "";
    }

    public Employee(String n, String pr, String m, String p){
        this.nom = n;
        this.prenom = pr;
        this.mail = m;
        this.poste = p;
    }

    public void setId(int i){ this.id = i;}
    public void setNom(String n){ this.nom = n;}
    public void setPrenom(String p){ this.prenom = p;}
    public void setMail(String m){ this.mail = m;}
    public void setPoste(String p){ this.poste = p; }

    public int getId(){ return this.id;}
    public String getNom(){
        return this.nom;
    }
    public String getMail(){
        return this.mail;
    }
    public String getPrenom(){ return this.prenom; }
    public String getPoste(){ return this.poste; }

    public void setCompanyId(int i){ this.companyId = i;}
    public int getCompanyId(){ return this.companyId;}

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public Employee(Parcel in){
        this.nom = in.readString();
        this.prenom = in.readString();
        this.mail = in.readString();
        this.poste = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nom);
        dest.writeString(this.prenom);
        dest.writeString(this.mail);
        dest.writeString(this.poste);
    }
}
