package com.tus.garbagesorting.garbagesorting.Mapper;

import com.tus.garbagesorting.garbagesorting.Model.PictureInfo;
import com.tus.garbagesorting.garbagesorting.Model.User;


public interface PictureMapper {

    int upset(PictureInfo pictureInfo);

    User findByUrl(String url);

}
