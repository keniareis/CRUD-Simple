package com.keniareis.crud.controllers;

import com.keniareis.crud.Model.Paciente;
import com.keniareis.crud.exceptions.PacienteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pacientes")
public class pacienteController {
    private ArrayList<Paciente> pacientes = new ArrayList<>();

    //create
    @PostMapping
    public Paciente adicionarPaciente(@RequestBody Paciente payload){
        Long proximoId = (long) pacientes.size() + 1;
        payload.setId(proximoId);
        pacientes.add(payload);
        return payload;
    }

    //read
    @GetMapping
    public ArrayList<Paciente> listarPacientes(){
        return pacientes;
    }

    //Update
    @PutMapping("/{id}")
    public Paciente atualzarPaciente(@PathVariable UUID id, @RequestBody Paciente pacienteAtualizado) throws Exception{
        for (Paciente paciente: pacientes){
            if (paciente.getId().equals(id)){
                paciente.setNome(pacienteAtualizado.getNome());
                paciente.setIdade(pacienteAtualizado.getIdade());
                paciente.setEndereco(pacienteAtualizado.getEndereco());
                return paciente;
            }
        }
        throw new PacienteNotFoundException("Paciente com ID " + id + " nao encontrado");
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable UUID id){
        pacientes.removeIf(paciente -> paciente.getId().equals(id));
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<String> handlePatienceNotFoundException(PacienteNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
