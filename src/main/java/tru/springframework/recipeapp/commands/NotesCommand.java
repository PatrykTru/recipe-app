package tru.springframework.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private String recipeNotes;
    private Long id;
}
