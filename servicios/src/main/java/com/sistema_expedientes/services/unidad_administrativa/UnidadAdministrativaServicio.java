package com.sistema_expedientes.services.unidad_administrativa;

import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import com.sistema_expedientes.unidad_administrativa.dto.request.UnidadAdministrativaRequestDTO;
import com.sistema_expedientes.google.drive_main.service.GoogleDriveService;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadAdministrativaServicio implements UnidadAdministrativaMetodos {

    private final UnidadAdministrativaRepositorio repositorio;

    private final ModelMapper mapper;

    private final GoogleDriveService googleDriveService;

    public UnidadAdministrativaServicio(UnidadAdministrativaRepositorio repositorio, ModelMapper mapper, GoogleDriveService googleDriveService){
        this.repositorio = repositorio;
        this.mapper = mapper;
        this.googleDriveService = googleDriveService;
    }

    @Override
    public UnidadAdministrativa get(String id) throws Exception {
        Optional<UnidadAdministrativa> in_db = repositorio.findById(id);

        return in_db.orElseThrow(Exception::new);

    }

    @Override
    public List<UnidadAdministrativa> list() {
        return repositorio.findAll();
    }

    @Override
    public UnidadAdministrativa create(UnidadAdministrativaRequestDTO request) throws Exception {

        UnidadAdministrativa to_bd = this.mapper.map(request, UnidadAdministrativa.class);

        if(request.getUnidadPrincipal() != null){
            to_bd.setUnidadPrincipal(
                    this.get(request.getUnidadPrincipal())
            );
            if(to_bd.getUnidadPrincipal() != null)
                to_bd.setGoogleDriveFolderId(
                        this.googleDriveService.createFolder(
                                to_bd.getClave(),
                                to_bd.getUnidadPrincipal().getGoogleDriveFolderId()
                        )
                );
        }else{
            to_bd.setGoogleDriveFolderId(
                    this.googleDriveService.createFolder(
                            to_bd.getClave(),
                            googleDriveService.getRootFolderId()
                    )
            );
        }


        return this.repositorio.save(to_bd);
    }

    @Override
    public UnidadAdministrativa put(String clave, UnidadAdministrativaRequestDTO request) throws Exception {
        UnidadAdministrativa in_bd = this.get(clave);

        in_bd.setNombre(request.getNombre());
        in_bd.setPiso(request.getPiso());
        in_bd.setExtensionTelefonica(request.getExtensionTelefonica());

        if(request.getUnidadPrincipal() != null){
            try{
                UnidadAdministrativa unidadPrincipal = this.get(request.getUnidadPrincipal());
                in_bd.setUnidadPrincipal(unidadPrincipal);
            }catch (Exception e){
                throw new Exception();
            }
        }

        return this.repositorio.save(in_bd);

    }
}
