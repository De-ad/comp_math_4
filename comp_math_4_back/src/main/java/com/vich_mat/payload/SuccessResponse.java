package com.vich_mat.payload;

import com.vich_mat.entities.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SuccessResponse {
    List<Result> resultList;


}
