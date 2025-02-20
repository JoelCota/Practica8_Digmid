package lopez.joel.digimid_228926.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import lopez.joel.digimid_228926.R
import lopez.joel.digimid_228926.Task
import lopez.joel.digimid_228926.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val selectedDays = mutableListOf<String>()

    // Mapa para las abreviaturas de los días
    private val daysMap = mapOf(
        "Monday" to "Lu",
        "Tuesday" to "Ma",
        "Wednesday" to "Mi",
        "Thursday" to "Ju",
        "Friday" to "Vi",
        "Saturday" to "Sa",
        "Sunday" to "Do"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.done.setOnClickListener {
            saveTask()
        }

        return binding.root
    }

    private fun saveTask() {
        val taskName = binding.name.text.toString().trim()
        if (taskName.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a task name", Toast.LENGTH_SHORT).show()
            return
        }

        // Obtener los días seleccionados y convertirlos a abreviaturas
        selectedDays.clear()  // Limpiar la lista de días antes de añadir nuevos
        if (binding.monday.isChecked) selectedDays.add(daysMap["Monday"] ?: "Lu")
        if (binding.tuesday.isChecked) selectedDays.add(daysMap["Tuesday"] ?: "Ma")
        if (binding.wednesday.isChecked) selectedDays.add(daysMap["Wednesday"] ?: "Mi")
        if (binding.thursday.isChecked) selectedDays.add(daysMap["Thursday"] ?: "Ju")
        if (binding.friday.isChecked) selectedDays.add(daysMap["Friday"] ?: "Vi")
        if (binding.saturday.isChecked) selectedDays.add(daysMap["Saturday"] ?: "Sa")
        if (binding.sunday.isChecked) selectedDays.add(daysMap["Sunday"] ?: "Do")

        // Determinar cómo mostrar los días seleccionados
        val daysDisplay = when {
            selectedDays.size == 7 -> "Todos los días"
            selectedDays.size == 5 && selectedDays.containsAll(
                listOf(
                    "Lu",
                    "Ma",
                    "Mi",
                    "Ju",
                    "Vi"
                )
            ) -> "Entre semana"

            selectedDays.size == 2 && selectedDays.containsAll(
                listOf(
                    "Sa",
                    "Do"
                )
            ) -> "Fin de semana"

            else -> selectedDays.joinToString(", ")
        }

        // Obtener la hora desde el EditText
        val taskTime = binding.time.text.toString().trim()
        if (taskTime.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a time", Toast.LENGTH_SHORT).show()
            return
        }


        // Crear la tarea
        val newTask = Task(taskName, daysDisplay, taskTime)

        // Mostrar la tarea en un Toast
        Toast.makeText(requireContext(), "Task saved: $newTask", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}