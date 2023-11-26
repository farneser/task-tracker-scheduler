package dev.farneser.tasktracker.scheduler.models


import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "columns")
class KanbanColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "column_name")
    var columnName: String? = null

    @Column(name = "user_id")
    var userId: Long? = null

    @Column(name = "order_number")
    var orderNumber: Long? = null
}
