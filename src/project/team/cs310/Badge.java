package project.team.cs310;

// Muhammad Shakir  ..... 
// Badge Updated

public class Badge 
{
    // Create a Variables
    private String description;
    private String id;
    
// Create a Constructor
    public Badge(String id, String d)
    {
        description = d;
        this.id = id;
    }
    
    // OverRiding the toString Java Object method
    // Formatting Output    
    @Override
    public String toString()
    {
        return "#" + id + " (" + description + ")";
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public String getId()
    {
        return id;
    }
}