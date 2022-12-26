package gdsc.session.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreDTO {
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String sector;

    @Builder
    public StoreDTO(String name, String address, Double latitude, Double longitude, String sector) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sector = sector;
    }
}
