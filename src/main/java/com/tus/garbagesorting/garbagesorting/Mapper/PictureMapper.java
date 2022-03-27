package com.tus.garbagesorting.garbagesorting.Mapper;

import com.tus.garbagesorting.garbagesorting.Model.PictureInfo;

import java.util.List;


public interface PictureMapper {

    int upset(PictureInfo pictureInfo);

    List<PictureInfo> findByPath(String url);

}
