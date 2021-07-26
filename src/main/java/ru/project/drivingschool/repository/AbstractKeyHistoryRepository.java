package ru.project.drivingschool.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.project.drivingschool.model.Address;
import ru.project.drivingschool.model.User;
import ru.project.drivingschool.model.common.AbstractKeyHistoryEntity;
import ru.project.drivingschool.model.embedded.History;
import ru.project.drivingschool.repository.jpa.JpaAddressRepository;
import ru.project.drivingschool.repository.jpa.JpaKeyRepository;
import ru.project.drivingschool.repository.jpa.JpaUserRepository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@NoRepositoryBean
public abstract class AbstractKeyHistoryRepository<T extends AbstractKeyHistoryEntity> {

    private JpaKeyRepository<T> repository;

    private JpaUserRepository userRepository;

    private JpaAddressRepository addressRepository;

    public AbstractKeyHistoryRepository(JpaKeyRepository<T> repository, JpaUserRepository userRepository, JpaAddressRepository addressRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public Page<T> getAll(Pageable page) {
        return repository.findAll(page);
    }

    public Page<T> getAll(Example<T> example, Pageable page) {
        return repository.findAll(example, page);
    }

    public T get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public boolean delete(Long id) {
        T t = this.get(id);
        Set<Long> ids = new HashSet<>();
        for (Field f : t.getClass().getDeclaredFields()) {
            if (f.getType().isAssignableFrom(Address.class)) {
                f.setAccessible(true);
                try {
                    Address a = (Address) f.get(t);
                    if (Objects.nonNull(a.getId()))
                        ids.add(a.getId());
                } catch (IllegalAccessException e) {
                    //ignore
                }
            }
        }
        ids.forEach(addressRepository::delete);
        return repository.delete(id)!=0;
    }

    @Transactional
    public T save(T t, Long userId) {
        if (!t.isNew() && !repository.existsById(t.id()))
            return null;
        User user = Objects.nonNull(userId) ? userRepository.getOne(userId) : null;

        if (t.isNew()) {
            t.getHistory().setCreatedBy(user);
        } else {
            T old = repository.getOne(t.id());
            History h = old.getHistory();
            h.setChangedBy(user);
            h.setChangedOn(LocalDateTime.now());
            t.setHistory(h);
        }
        return repository.save(t);
    }

}
