package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getSalarioFormatado() {
        return String.format("%,.2f", salario).replace(",", ".").replace(".", ",");
    }

    @Override
    public String toString() {
        return super.toString() + " || funcao: " + funcao + " || Salario: R$ " + getSalarioFormatado();
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
