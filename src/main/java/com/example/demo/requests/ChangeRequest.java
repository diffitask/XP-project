package com.example.demo.requests;

import com.example.demo.models.LinkModel;
import lombok.Data;

@Data
public class ChangeRequest {
    LinkModel oldLink;
    String newName;
    String newUrl;
    String newDescription;
}
