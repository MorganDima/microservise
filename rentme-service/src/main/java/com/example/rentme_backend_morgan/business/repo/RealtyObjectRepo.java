package com.example.rentme_backend_morgan.business.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.rentme_backend_morgan.business.entities.RealtyObject;

@Repository
public interface RealtyObjectRepo extends JpaRepository<RealtyObject, Long> {

    @Query(value = "SELECT * FROM realtyobject WHERE id=?1", nativeQuery = true)
    RealtyObject findRealtyObject(long idObject);

//    @Query(value = "SELECT ro.name_object FROM realtyobject ro, address ad " +
//            "WHERE " +
//            "ro.appt_number=?1 " +
//            "AND ad.country_name=?2 " +
//            "AND ad.city_name=?3 " +
//            "AND ad.street_name=?4 " +
//            "AND ad.number_house=?5 " +
//            "AND ad.block_number=?6", nativeQuery = true)
//    String existsByInfo(String aNumber,
//                        String cName,
//                        String ccName,
//                        String strName,
//                        int nHouse,
//                        String bNumber);

    @Query(value = "SELECT realtyobject.name_object " +
                    "FROM realtyobject " +
                    "WHERE appt_number=?1 AND address_id=?2 ", nativeQuery = true)
    String existsByInfo(String aNumber, Long addId);

}

