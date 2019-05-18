package android.rxjava.labs.instantsearchwithretrofit.model;

import com.google.gson.annotations.SerializedName;
//{ "name": "Tom Hardy", "image": "https://api.androidhive.info/json/images/tom_hardy.jpg",
// "phone": "(541) 754-3010", "email": "tom@rxjava.wtf", "source": "gmail" }
public class Contact {
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("source")
    private String source;

    public Contact() {
    }

    public Contact(String name, String image, String phone, String email, String source) {
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
