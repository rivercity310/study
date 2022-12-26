package gdsc.session.entity;

import gdsc.session.DTO.StoreDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "location")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name")
    private String name;

    @Column(name = "store_address")
    private String address;

    @Column(name = "store_latitude")
    private Double latitude;

    @Column(name = "store_longitude")
    private Double longitude;

    @Column(name = "store_sector")
    private String sector;

    @Builder
    public Store(StoreDTO storeDTO) {
        this.name = storeDTO.getName();
        this.address = storeDTO.getAddress();
        this.latitude = storeDTO.getLatitude();
        this.longitude = storeDTO.getLongitude();
        this.sector = storeDTO.getSector();
    }
}
