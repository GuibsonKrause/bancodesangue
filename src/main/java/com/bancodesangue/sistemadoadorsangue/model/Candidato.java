package com.bancodesangue.sistemadoadorsangue.model;

import java.util.Date;
import java.util.Locale;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Temporal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "candidatos")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "rg")
    private String rg;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNasc;
    
    @Column(name = "sexo")
    private String sexo;
    
    @Column(name = "mae")
    private String mae;
    
    @Column(name = "pai")
    private String pai;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "endereco")
    private String endereco;
    
    @Column(name = "numero")
    private Integer numero;
    
    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "cidade")
    private String cidade;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "telefone_fixo")
    private String telefoneFixo;
    
    @Column(name = "celular")
    private String celular;
    
    @Column(name = "altura")
    private Double altura;
    
    @Column(name = "peso")
    private Double peso;
    
    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getRg() { return rg; }
    public Date getDataNasc() { return dataNasc; }
    public String getSexo() { return sexo; }
    public String getMae() { return mae; }
    public String getPai() { return pai; }
    public String getEmail() { return email; }
    public String getCep() { return cep; }
    public String getEndereco() { return endereco; }
    public Integer getNumero() { return numero; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getTelefoneFixo() { return telefoneFixo; }
    public String getCelular() { return celular; }
    public Double getAltura() { return altura; }
    public Double getPeso() { return peso; }
    public String getTipoSanguineo() { return tipoSanguineo; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setRg(String rg) { this.rg = rg; }
    public void setDataNasc(Date dataNasc) { this.dataNasc = dataNasc; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setMae(String mae) { this.mae = mae; }
    public void setPai(String pai) { this.pai = pai; }
    public void setEmail(String email) { this.email = email; }
    public void setCep(String cep) { this.cep = cep; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setTelefoneFixo(String telefoneFixo) { this.telefoneFixo = telefoneFixo; }
    public void setCelular(String celular) { this.celular = celular; }
    public void setAltura(Double altura) { this.altura = altura; }
    public void setPeso(Double peso) { this.peso = peso; }
    public void setTipoSanguineo(String tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }
    public void setDataNascFromString(String dataNascStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            this.dataNasc = formatter.parse(dataNascStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
