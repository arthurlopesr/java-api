package com.client.ws.rasmooplus.presentation.controllers.doc;

import com.client.ws.rasmooplus.domain.entities.UserEntity;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;
import com.client.ws.rasmooplus.presentation.error.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Usuário")
public interface UserControllerDoc {
    @Operation(summary = "Serviço de criação de um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário Criado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Erro ao salvar usuário",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )})
    })
    ResponseEntity<UserEntity> create(UserDTO userDTO);

    @Operation(summary = "Serviço de listagem de todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os usuários",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar todos os usuários",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )})
    })
    ResponseEntity<List<UserEntity>> findAll();

    @Operation(summary = "Serviço de busca de um único usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de um único usuário",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar o usuário",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )})
    })
    ResponseEntity<UserEntity> findById(Long id);

    @Operation(summary = "Serviço de atualização de um único usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizadar usuário",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )})
    })
    ResponseEntity<UserEntity> update(Long id, UserDTO userDTO);

    @Operation(summary = "Serviço de deleção de um único usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Erro ao deletadar usuário",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )})
    })
    ResponseEntity<Void> delete(Long id);
}
