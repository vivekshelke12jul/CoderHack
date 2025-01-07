package com.crio.coderHack.exchanges;

import com.crio.coderHack.model.Badge;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String score;
    private ArrayList<Badge> badges;
}
