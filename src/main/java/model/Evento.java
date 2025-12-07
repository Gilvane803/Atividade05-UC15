package model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String local;
    private Date data_evento;

    // @ManyToMany
    // private Escola escola;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public Date getData_evento() {
        return data_evento;
    }
    public void setData_evento(Date data_evento) {
        this.data_evento = data_evento;
    }
    // public Escola getEscola() {
    //     return escola;
    // }
    // public void setEscola(Escola escola) {
    //     this.escola = escola;
    // }
}
