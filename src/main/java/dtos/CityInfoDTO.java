package dtos;

import java.util.ArrayList;
import java.util.List;

import entities.CityInfo;

public class CityInfoDTO {
    private long id;
    private String zipCode;
    private String city;
    
    public CityInfoDTO(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public CityInfoDTO(CityInfo cityInfo) {
        if(cityInfo.getId() != null)
            this.id = cityInfo.getId();
        this.zipCode = cityInfo.getZipCode();
        this.city = cityInfo.getCity();
    }

    public static List<CityInfoDTO> getDtos(List<CityInfo> cityInfo) {
        List<CityInfoDTO> addressDTO = new ArrayList<CityInfoDTO>();
        cityInfo.forEach(_cityInfo -> addressDTO.add(new CityInfoDTO(_cityInfo)));
        return addressDTO;
    }

    public long getId() {
        return id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }
}
