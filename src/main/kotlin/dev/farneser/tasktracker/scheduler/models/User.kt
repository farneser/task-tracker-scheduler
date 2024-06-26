package dev.farneser.tasktracker.scheduler.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", indexes = [Index(name = "email_index", columnList = "email", unique = true)])
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    var id: Long = 0

    @Column(name = "email", nullable = false, unique = true)
    var email: String = ""

    @Column(name = "is_subscribed", nullable = false)
    var isSubscribed: Boolean? = null

    @Column(name = "is_enabled", nullable = false)
    var isEnabled: Boolean? = null

    @Column(name = "is_locked", nullable = false)
    var isLocked: Boolean? = null
}
