
package project.team.cs310;

public class Badge {

        private String description;
        private String id;
        
        public Badge(String id, String d){
            description = d;
            this.id = id;
        }
        
        //blank constructor
        public Badge(){
            
        }
    
    @Override
    public String toString(){
        return "#" + id + " (" + description + ")";
    }
    // set badge description
    public void setDescription(String description){
        this.description = description;
    }
    //set badge ID
    public void setId(String id) {    
        this.id = id;
    }
    //get badge descrption
    public String getDescription() {
        return description;
    }
    //get badge ID
    public String getId() {
        return id;
    }
}
