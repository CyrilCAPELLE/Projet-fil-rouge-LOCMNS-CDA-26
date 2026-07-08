package edu.mns.cda.projetfilrougelocmnscda26.mock;

import edu.mns.cda.projetfilrougelocmnscda26.dao.MaterielDao;
import edu.mns.cda.projetfilrougelocmnscda26.model.Materiel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockMaterielDao implements MaterielDao {

    @Override
    public Optional<Materiel> findById(Integer id) {
        return Optional.empty();
    }

    @Override public void flush() {}
    @Override public <S extends Materiel> S saveAndFlush(S entity) { return null; }
    @Override public <S extends Materiel> List<S> saveAllAndFlush(Iterable<S> entities) { return List.of(); }
    @Override public void deleteAllInBatch(Iterable<Materiel> entities) {}
    @Override public void deleteAllByIdInBatch(Iterable<Integer> integers) {}
    @Override public void deleteAllInBatch() {}
    @Override public Materiel getOne(Integer integer) { return null; }
    @Override public Materiel getById(Integer integer) { return null; }
    @Override public Materiel getReferenceById(Integer integer) { return null; }
    @Override public <S extends Materiel> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
    @Override public <S extends Materiel> List<S> findAll(Example<S> example) { return List.of(); }
    @Override public <S extends Materiel> List<S> findAll(Example<S> example, Sort sort) { return List.of(); }
    @Override public <S extends Materiel> Page<S> findAll(Example<S> example, Pageable pageable) { return null; }
    @Override public <S extends Materiel> long count(Example<S> example) { return 0; }
    @Override public <S extends Materiel> boolean exists(Example<S> example) { return false; }
    @Override public <S extends Materiel, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
    @Override public <S extends Materiel> S save(S entity) { return null; }
    @Override public <S extends Materiel> List<S> saveAll(Iterable<S> entities) { return List.of(); }
    @Override public boolean existsById(Integer integer) { return false; }
    @Override public List<Materiel> findAll() { return List.of(); }
    @Override public List<Materiel> findAllById(Iterable<Integer> integers) { return List.of(); }
    @Override public long count() { return 0; }
    @Override public void deleteById(Integer integer) {}
    @Override public void delete(Materiel entity) {}
    @Override public void deleteAllById(Iterable<? extends Integer> integers) {}
    @Override public void deleteAll(Iterable<? extends Materiel> entities) {}
    @Override public void deleteAll() {}
    @Override public List<Materiel> findAll(Sort sort) { return List.of(); }
    @Override public Page<Materiel> findAll(Pageable pageable) { return null; }
}
