package org.example;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="manufacturer_2")
@DiscriminatorValue("1")
public class Manufacturer extends Company
{
    private String link = "";

    @OneToOne(orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "COMPUTERSYSTEM_ID", foreignKey = @ForeignKey())
    private ManufacturerComputerSystem computerSystem = new ManufacturerComputerSystem(this);

    public Manufacturer()
    {
    }

    public Manufacturer(ManufacturerComputerSystem computerSystem)
    {
        this.computerSystem = computerSystem;
    }
}
