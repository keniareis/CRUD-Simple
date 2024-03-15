package com.keniareis.crud.controllers;

import com.keniareis.crud.Model.Paciente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class pacienteController {
    private List<Paciente> pacientes = new ArrayList<>();

    //create
    @PostMapping
    public Paciente adicionarPaciente(@RequestBody Paciente paciente){
        pacientes.add(paciente);
        return paciente;
    }

    //read
    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacientes;
    }

    //Update
    @PutMapping("/{id}")
    public Paciente atualzarPaciente(@PathVariable UUID id, @RequestBody Paciente pacienteAtualizado){
        for (Paciente paciente: pacientes){
            if (paciente.getId().equals(id)){
                paciente.setNome(pacienteAtualizado.getNome());
                paciente.setIdade(pacienteAtualizado.getIdade());
                paciente.setEndereco(pacienteAtualizado.getEndereco());
                return paciente;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable UUID id){
        pacientes.removeIf(paciente -> paciente.getId().equals(id));
    }
}
