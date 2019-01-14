package com.cpscs.omsadmin.dto.do2dto;

import com.cpscs.omsadmin.domain.UserDO;
import com.cpscs.omsadmin.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-14T13:48:47+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public UserDTO do2dto(UserDO person) {
        if ( person == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId( person.getUserId() );
        userDTO.setUsername( person.getUsername() );
        userDTO.setName( person.getName() );
        userDTO.setDeptId( person.getDeptId() );
        userDTO.setDeptName( person.getDeptName() );
        userDTO.setEmail( person.getEmail() );
        userDTO.setMobile( person.getMobile() );
        userDTO.setStatus( person.getStatus() );
        userDTO.setUserIdCreate( person.getUserIdCreate() );
        userDTO.setGmtCreate( person.getGmtCreate() );
        userDTO.setGmtModified( person.getGmtModified() );
        List<Long> list = person.getroleIds();
        if ( list != null ) {
            userDTO.setRoleIds( new ArrayList<Long>( list ) );
        }
        else {
            userDTO.setRoleIds( null );
        }
        userDTO.setSex( person.getSex() );
        userDTO.setBirth( person.getBirth() );
        userDTO.setPicId( person.getPicId() );
        userDTO.setLiveAddress( person.getLiveAddress() );
        userDTO.setHobby( person.getHobby() );
        userDTO.setProvince( person.getProvince() );
        userDTO.setCity( person.getCity() );
        userDTO.setDistrict( person.getDistrict() );

        return userDTO;
    }

    @Override
    public List<UserDTO> dos2dtos(List<UserDO> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDTO> list1 = new ArrayList<UserDTO>( list.size() );
        for ( UserDO userDO : list ) {
            list1.add( do2dto( userDO ) );
        }

        return list1;
    }
}
