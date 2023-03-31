package org.example;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="manufacturer_computer_system_2")
@DiscriminatorValue("2")
public class ManufacturerComputerSystem extends ComputerSystem
{
    public ManufacturerComputerSystem()
    {
    }

    public ManufacturerComputerSystem(Manufacturer manufacturer)
    {
        setOwner(manufacturer);
    }
}
