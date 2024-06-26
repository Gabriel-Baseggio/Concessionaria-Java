package net.weg.topcar.dao;

import net.weg.topcar.model.exceptions.PermissaoNegadaException;
import net.weg.topcar.model.exceptions.ObjetoExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.List;

public interface IBanco<T, ID> {

    List<T> buscarTodos();

    T buscarUm(ID id) throws ObjetoNaoEncontradoException;

    T adicionar(T novo) throws ObjetoExistenteException;

    void remover(ID id) throws ObjetoNaoEncontradoException, PermissaoNegadaException;

    Boolean existe(ID id);

    void alterar(ID id, T novo) throws ObjetoNaoEncontradoException;

}
