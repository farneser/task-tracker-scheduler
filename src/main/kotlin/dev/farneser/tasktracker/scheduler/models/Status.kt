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
@Table(name = "statuses")
class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "status_name")
    var statusName: String? = null

    @Column(name = "project_id")
    var projectId: Long? = null

    @Column(name = "is_completed")
    var isCompleted: Boolean? = null

    @Column(name = "order_number")
    var orderNumber: Long? = null
}
