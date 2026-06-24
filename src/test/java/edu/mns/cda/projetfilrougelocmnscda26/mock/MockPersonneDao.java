package edu.mns.cda.projetfilrougelocmnscda26.mock;

import edu.mns.cda.projetfilrougelocmnscda26.dao.PersonneDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Personne;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockPersonneDao implements PersonneDao {

    @Override
    public Optional<Personne> findById(Integer id) {
        if (id == 1) {
            Personne personne = new Personne();
            personne.setId(1);
            return Optional.of(personne);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Personne> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Personne> retourneListeSelonProfile(String profile) {
        return List.of();
    }

    @Override public void flush() {}
    @Override public <S extends Personne> S saveAndFlush(S entity) { return null; }
    @Override public <S extends Personne> List<S> saveAllAndFlush(Iterable<S> entities) { return List.of(); }
    @Override public void deleteAllInBatch(Iterable<Personne> entities) {}
    @Override public void deleteAllByIdInBatch(Iterable<Integer> integers) {}
    @Override public void deleteAllInBatch() {}
    @Override public Personne getOne(Integer integer) { return null; }
    @Override public Personne getById(Integer integer) { return null; }
    @Override public Personne getReferenceById(Integer integer) { return null; }
    @Override public <S extends Personne> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
    @Override public <S extends Personne> List<S> findAll(Example<S> example) { return List.of(); }
    @Override public <S extends Personne> List<S> findAll(Example<S> example, Sort sort) { return List.of(); }
    @Override public <S extends Personne> Page<S> findAll(Example<S> example, Pageable pageable) { return null; }
    @Override public <S extends Personne> long count(Example<S> example) { return 0; }
    @Override public <S extends Personne> boolean exists(Example<S> example) { return false; }
    @Override public <S extends Personne, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
    @Override public <S extends Personne> S save(S entity) { return null; }
    @Override public <S extends Personne> List<S> saveAll(Iterable<S> entities) { return List.of(); }
    @Override public boolean existsById(Integer integer) { return false; }
    @Override public List<Personne> findAll() { return List.of(); }
    @Override public List<Personne> findAllById(Iterable<Integer> integers) { return List.of(); }
    @Override public long count() { return 0; }
    @Override public void deleteById(Integer integer) {}
    @Override public void delete(Personne entity) {}
    @Override public void deleteAllById(Iterable<? extends Integer> integers) {}
    @Override public void deleteAll(Iterable<? extends Personne> entities) {}
    @Override public void deleteAll() {}
    @Override public List<Personne> findAll(Sort sort) { return List.of(); }
    @Override public Page<Personne> findAll(Pageable pageable) { return null; }
}
