package com.example.rentme_backend_morgan.business.service.google;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.rentme_backend_morgan.business.dto.ResponseDto.AddressGoogleDto;
import com.example.rentme_backend_morgan.business.dto.ResponseDto.Candidates;
import com.example.rentme_backend_morgan.business.dto.fromFront.RealtyObjectDto;
import java.net.URI;
import java.net.URISyntaxException;
import static com.example.rentme_backend_morgan.business.bussinesChecks.BussinesChecks.*;

public class ConfigRestForGoogle {

    private final static Logger logger = LoggerFactory.getLogger(ConfigRestForGoogle.class);

    private static final String MyAPI = "&key=AIzaSyCYJgRwB8COK1IezpCfnULOuJuCqKAW4yg";
    private static final String HTTPGOOGLE = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=";


    public static Candidates createCandidates(RealtyObjectDto dto) {
        String FORMATTED_ADDRESS =
                dto.getCountryName() + "." +
                        dto.getCityName() + "." +
                        dto.getStreetName() + "." +
                        dto.getNumberOfHouse();

        String urii = HTTPGOOGLE +
                FORMATTED_ADDRESS +
                "&language=en" +
                "&inputtype=textquery" +
                "&fields=" +
                "formatted_address" + "%2Cname" + "%2Cgeometry" + "%2Ctypes" +
                MyAPI;

        RestTemplate rest = new RestTemplate();
        RequestEntity<Void> request = null;

        try {
            request = RequestEntity.get(new URI(urii)).build();
        } catch (URISyntaxException e) {
            logger.info(e.getMessage());
        }
        ResponseEntity<AddressGoogleDto> response = rest.exchange(request, AddressGoogleDto.class);
        checkAddress(response); //TODO check if address is not null
        Candidates complitAddress = response.getBody().getCandidates()[0];

        return complitAddress;
    }
}